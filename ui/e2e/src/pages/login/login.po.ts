import { browser, by, element, promise, ElementFinder, ElementArrayFinder } from 'protractor';

export class LoginPage {

    navigateToLoginPage() {
        return browser.get('/login');
    }

    sendEmailForLogin() {
        return element(by.id('loginemail'));
    }

    sendPasswordForLogin() {
        return element(by.id('loginpassword'));
    }
    getLoginButton() {
        return element(by.id('loginButton'));
    }

   getErrorMessage() {
        return element(by.className('alert alert-danger'));
    }  

}
