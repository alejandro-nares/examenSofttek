package com.softtek.authorization.transaction.commons.util;

import java.io.PrintStream;
import java.io.PrintWriter;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;

@XmlRootElement
public class ServiceException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public ServiceException() {}
  
 private static final String CAUSED_BY = "\nCausado por: ";
  
  private Throwable cause = null;
  
  private String codError = null;
  
  public String getCodError() {
    return this.codError;
  }
  
  public void setCodError(String codError) {
    this.codError = codError;
  }
  
  private HttpStatus httpStatus = null;
  
  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }
  
  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }
  
  public ServiceException(String msg) {
    super(msg);
  }
  
  public ServiceException(String msg, String codError) {
    super(msg);
    this.codError = codError;
  }
  
  public ServiceException(String msg, String codError, HttpStatus httpStatus) {
    super(msg);
    this.codError = codError;
    this.httpStatus = httpStatus;
  }
  
  public ServiceException(Throwable cause) {
    this.cause = cause;
  }
  
  public ServiceException(String msg, Throwable cause) {
    super(msg);
    this.cause = cause;
  }
  
  public ServiceException(String msg, Throwable cause, String codError) {
    super(msg);
    this.cause = cause;
    this.codError = codError;
  }
  
  public ServiceException(String msg, Throwable cause, String codError, HttpStatus httpStatus) {
    super(msg);
    this.cause = cause;
    this.codError = codError;
    this.httpStatus = httpStatus;
  }
  
  public Throwable getCause() {
    return this.cause;
  }
  
  public String toString() {
    if (this.cause == null)
      return super.toString(); 
    return String.valueOf(super.toString()) + "\nCausado por: " + this.cause.toString();
  }
  
  public void printStackTrace() {
    super.printStackTrace();
    if (this.cause != null) {
      System.err.println("\nCausado por: ");
      this.cause.printStackTrace();
    } 
  }
  
  public void printStackTrace(PrintStream ps) {
    super.printStackTrace(ps);
    if (this.cause != null) {
      ps.println("\nCausado por: ");
      this.cause.printStackTrace(ps);
    } 
  }
  
  public void printStackTrace(PrintWriter pw) {
    super.printStackTrace(pw);
    if (this.cause != null) {
      pw.println("\nCausado por: ");
      this.cause.printStackTrace(pw);
    } 
  }
  
}