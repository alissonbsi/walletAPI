package com.wallet.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.UserWalletService;

@RestController
@RequestMapping(path = "users-wallet")
public class UserWalletController {

	@Autowired
	private UserWalletService userWalletService;
	
	@PostMapping
	public ResponseEntity<Response<UserWalletDTO>> create (@Valid @RequestBody UserWalletDTO userWalletDTO, BindingResult result){
		
		Response<UserWalletDTO> response = new Response<UserWalletDTO>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		UserWallet userWallet = userWalletService.save(this.converteDtoToEntity(userWalletDTO));
		
		response.setData(this.convertEntityToDto(userWallet));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public UserWallet converteDtoToEntity(UserWalletDTO userWalletDTO) {
		
		UserWallet userWallet = new UserWallet();
		
		User user = new User();
		user.setId(userWalletDTO.getUsers());
		userWallet.setId(userWalletDTO.getId());
		
		Wallet wallet = new Wallet();
		wallet.setId(userWalletDTO.getWallet());
		
		userWallet.setId(userWalletDTO.getId());
		userWallet.setUsers(user);
		userWallet.setWallet(wallet);
		
		return userWallet;
		
	}
	
	public UserWalletDTO convertEntityToDto (UserWallet userWallet) {
		
		UserWalletDTO userWalletDTO = new UserWalletDTO();
		userWalletDTO.setId(userWallet.getId());
		userWalletDTO.setUsers(userWallet.getUsers().getId());
		userWalletDTO.setWallet(userWallet.getWallet().getId());
		
		return userWalletDTO;
	}
}
