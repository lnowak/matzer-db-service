package com.matzer.db.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.matzer.db.api.dto.object.AccountDto;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.security.RandomCodeGenerator;
import com.matzer.db.api.service.IAccountDtoService;
import com.matzer.db.api.service.IEmailService;
import com.matzer.db.api.service.exception.ApiException;
import com.matzer.db.commons.utils.HashUtils;
import com.matzer.db.commons.utils.HashUtils.HashType;
import com.matzer.db.dao.IAccountDao;
import com.matzer.db.entity.Account;
import com.matzer.db.enums.EmailTemplateType;

/**
 * 
 * Implementation of the DTO service for account object.
 * 
 * @author lkawon@gmail.com
 *
 */
@Component
public class AccountDtoService implements IAccountDtoService {

	/**
	 * Activation code length.
	 */
	private static final int ACTIVATION_CODE_LENGTH = 16;
	
	/**
	 * Reset code length.
	 */
	private static final int RESET_CODE_LENGTH = 16;
	
	/**
	 * Account DAO.
	 */
	@Autowired
	private IAccountDao accountDao;
	
	/**
	 * Email service;
	 */
	@Autowired
	private IEmailService emailService;
	
	/**
	 * Dozer mapper.
	 */
	@Autowired
	private Mapper mapper;
	
	
	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#getAllAccounts()
	 */
	@Override
	@Transactional(readOnly = true)
	public final List<AccountDto> getAllAccounts() {
		List<AccountDto> accountsDto = new ArrayList<AccountDto>();
		for (Account account : accountDao.findAll()) {		
			accountsDto.add(mapper.map(account,  AccountDto.class));
		}
		
		return accountsDto;
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#getAccount(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public final AccountDto getAccount(String email) {
		AccountDto accountDto = null;
		Account account = accountDao.findByEmail(email);
		if (account != null) {					
			accountDto = mapper.map(account, AccountDto.class);
		}
		
		return accountDto;
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#addAccount(com.matzer.db.api.dto.object.AccountDto)
	 */
	@Override
	@Transactional
	public final void addAccount(AccountDto accountDto) {
		if (accountDto != null) {
			Account existingAccount = accountDao.findByEmail(accountDto.getEmail());
			if (existingAccount == null) {
				Account account = mapper.map(accountDto, Account.class);
				account.setPassword(HashUtils.generateHash(HashType.SHA, account.getPassword()));
				if (account.getIsActive() != null && !account.getIsActive()) {
					account.setActivationCode(RandomCodeGenerator.generateCode(ACTIVATION_CODE_LENGTH));
					emailService.sendEmail(EmailTemplateType.ACTIVATION, account);
				}														
				
				accountDao.save(account);
			} else {
				throw new ApiException(ErrorCode.EMAIL_TAKEN);
			}
		} else {
			throw new ApiException(ErrorCode.EMPTY_DTO_OBJECT);
		}		
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#resendActivationEmail(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public final void resendActivationEmail(String email) {
		Account account = accountDao.findByEmail(email);
		if (account != null) {
			emailService.sendEmail(EmailTemplateType.ACTIVATION, account);
		} else {
			throw new ApiException(ErrorCode.ACCOUNT_WAS_NOT_FOUND);
		}		
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#activateAccount(java.lang.String)
	 */
	@Override
	@Transactional
	public final void activateAccount(String activationCode) {
		if (activationCode != null) {
			Account account = accountDao.findByActivationCode(activationCode);
			if (account != null) {				
				account.setActivationCode(null);
				account.setIsActive(true);				
				
				accountDao.save(account);
			} else {
				throw new ApiException(ErrorCode.ACTIVATION_CODE_IS_INCORRECT);
			}
		} else {
			throw new ApiException(ErrorCode.ACTIVATION_CODE_IS_EMPTY);
		}		
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#generateResetPasswordCode(java.lang.String)
	 */
	@Override
	@Transactional
	public final void generateResetPasswordCode(String email) {
		Account account = accountDao.findByEmail(email);
		if (account != null) {
			account.setResetCode(RandomCodeGenerator.generateCode(RESET_CODE_LENGTH));			
			emailService.sendEmail(EmailTemplateType.PASSWORD_RESET, account);			
			
			accountDao.save(account);
		} else {
			throw new ApiException(ErrorCode.ACCOUNT_WAS_NOT_FOUND);
		}		
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#resetPassword(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public final void resetPassword(String resetCode, String password) {
		if (resetCode != null) {
			Account account = accountDao.findByResetCode(resetCode);
			if (account != null) {				
				account.setResetCode(null);
				if (password != null) {
					account.setPassword(HashUtils.generateHash(HashType.SHA, password));
				}
				
				accountDao.save(account);
			} else {
				throw new ApiException(ErrorCode.ACTIVATION_CODE_IS_INCORRECT);
			}
		} else {
			throw new ApiException(ErrorCode.ACTIVATION_CODE_IS_EMPTY);
		}
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#updateAccount(com.matzer.db.api.dto.object.AccountDto)
	 */
	@Override
	@Transactional
	public final void updateAccount(AccountDto accountDto) {
		if (accountDto != null) {			
			Account existingAccount = accountDao.findByEmail(accountDto.getEmail());
			if (existingAccount != null) {
				Account newAccount = mapper.map(accountDto, Account.class);
				
				existingAccount.setBirthDate(newAccount.getBirthDate());
				existingAccount.setCity(newAccount.getCity());				
				existingAccount.setFacebookId(newAccount.getFacebookId());
				existingAccount.setFirstName(newAccount.getFirstName());
				existingAccount.setGender(newAccount.getGender());
				existingAccount.setLastName(newAccount.getLastName());
				existingAccount.setModificationDate(new Date());
				existingAccount.setProvince(newAccount.getProvince());
				
				//existingAccount.setEmail(newAccount.getEmail());
				//TODO: email notification
				
				accountDao.save(existingAccount);
			} else {
				throw new ApiException(ErrorCode.ENTITY_NOT_FOUND);
			}
		} else {
			throw new ApiException(ErrorCode.EMPTY_DTO_OBJECT);
		}			
	}

	/* (non-Javadoc)
	 * @see com.matzer.db.api.service.IAccountDtoService#deleteAccount(java.lang.String)
	 */
	@Override
	@Transactional
	public final void deleteAccount(String email) {
		Account account = accountDao.findByEmail(email);
		if (account != null) {
			emailService.sendEmail(EmailTemplateType.ACCOUNT_REMOVAL, account);
			accountDao.delete(account);
		} else {
			throw new ApiException(ErrorCode.ENTITY_NOT_FOUND);
		}		
	}
}
