package org.jbehave.osgi.examples.taskweb.integration_tests.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractTaskWebPage extends WebDriverPage {

	public AbstractTaskWebPage(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	public WebElement findElementWithWait(final By by, long timeOutInSeconds) {
		WebElement myDynamicElement = (new WebDriverWait(webDriver(),
				timeOutInSeconds)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				return d.findElement(by);
			}
		});

		return myDynamicElement;
	}

	public void waitUntilElementIsEnabled(final By by,
			final long timeOutInSeconds) {
		new WebDriverWait(webDriver(), timeOutInSeconds)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return d.findElement(by).isEnabled();
					}
				});
	}

	public void waitUntilElementIsDisabled(final By by,
			final long timeOutInSeconds) {
		new WebDriverWait(webDriver(), timeOutInSeconds)
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return d.findElement(by).isEnabled();
					}
				});
	}

	public void waitUntilElementBePresent(final By by, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(webDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitUntilDialogBeClosed(final String dialogId) {
		WebDriverWait wait = new WebDriverWait(webDriver(), 5);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					return d.findElement(By.id(dialogId)) == null;					
				} catch (Exception e) {
					return true;
				}
			}
		});
	}

	public void waitUntilNotificationAppears(final String notificationMessage,
			long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(webDriver(), timeOutInSeconds);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				By by = By.cssSelector("v-Notification");

				return d.findElement(by).getText().equals(notificationMessage);
			}
		});
	}

	public void waitAndClickOnCurrentNotification() {
		WebElement myDynamicElement = findElementWithWait(
				By.cssSelector("v-Notification"), 5);
		myDynamicElement.click();
	}

}
