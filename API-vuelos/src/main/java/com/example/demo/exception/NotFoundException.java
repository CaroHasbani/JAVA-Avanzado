package com.example.demo.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

		public NotFoundException(Integer id) {
			super(String.format("this %d was not found",id));
		}
}