package com.blog.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.repository.UserRepository;
import com.blog.response.ErrorResponse;
import com.blog.response.Response;
import com.blog.response.SuccessResponse;

@Service
public class UserService extends BaseService {
	private UserRepository repository;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}

	public Response findAllData() {
		try {
			List<User> all = repository.findAll();
			return new SuccessResponse(HttpStatus.OK, "user list fetch", all);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	public Response findOneData(long id) {
		try {
			Optional<User> optional = repository.findById(id);
			if (optional.isEmpty())
				return new ErrorResponse(HttpStatus.NOT_FOUND, "User not Found", null);

			return new SuccessResponse(HttpStatus.OK, "User Found", optional.get());
		} catch (Exception e) {
			return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	public Response addData(User user) {
		try {
			Optional<User> checkEmail = repository.findByEmail(user.getEmail());
			if (checkEmail.isPresent())
				return new ErrorResponse(HttpStatus.BAD_REQUEST, "Email already exists");

			User save = repository.save(user);

			return new SuccessResponse(HttpStatus.OK, "User added successfully", save);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}

	public Response editData(User user) {
		try {
			if (user.getId() != null) {
				Optional<User> checkUser = repository.findById(user.getId());
				if (checkUser.isEmpty())
					return new ErrorResponse(HttpStatus.NOT_FOUND, "User not found");
				user.setCreatedAt(checkUser.get().getCreatedAt());
			}

			Optional<User> checkEmail = repository.findByEmail(user.getEmail(), user.getId());
			if (checkEmail.isPresent())
				return new ErrorResponse(HttpStatus.BAD_REQUEST, "Email already exists");

			User save = repository.save(user);

			return new SuccessResponse(HttpStatus.OK, "User updated successfully", save);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}
