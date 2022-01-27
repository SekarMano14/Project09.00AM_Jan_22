package org.pages;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBys({ @FindBy(id = "email"), 
		@FindBy(xpath = "//input[@name='email']") })
	private List<WebElement> txtUsername;

	@FindAll({ @FindBy(id = "pass"), 
		@FindBy(xpath = "//input[@name='pass']") })
	private WebElement txtPassword;

	@FindBy(name = "login")
	private WebElement btnLogin;

	public List<WebElement> getTxtUsername() {
		return txtUsername;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public void login(String u,String p) {
		sendKeys(getTxtUsername().get(0), u);
		sendKeys(getTxtPassword(), p);
		btnClick(getBtnLogin());
	}

}
