import { LoginPage } from './login.po';
import { protractor, browser } from 'protractor';


describe('Login page', () => {
    let page: LoginPage;
   const EC = protractor.ExpectedConditions;
    beforeEach(() => {
        page = new LoginPage();
        page.navigateToLoginPage();
    });

    it('should be able to login as user', () => {
        page.sendEmailForLogin().sendKeys('sarbo@gmail.com');
        page.sendPasswordForLogin().sendKeys('1234');
        page.getLoginButton().click();
       
        browser.sleep(2000);
        expect(browser.driver.getCurrentUrl()).toContain('search');
    });

    it('should be able to login as admin', () => {
        page.sendEmailForLogin().sendKeys('soumya@gmail.com');
        page.sendPasswordForLogin().sendKeys('1234');
        page.getLoginButton().click();
        browser.sleep(2000)
        expect(browser.driver.getCurrentUrl()).toContain('search');
    });
     /* it('should be not be able to login if email is wrong', async() => {
        page.sendEmailForLogin().sendKeys('sz@gmail.com');
        page.sendPasswordForLogin().sendKeys('A123456');
        page.getLoginButton().click();
        browser.wait(EC.visibilityOf(page.getErrorMessage()));
        expect(page.getErrorMessage().getText()).toBe('Invalid Email Id or Password.');
    });   */
});



// https://trailheadtechnology.com/ui-automation-testing-of-angular-apps-using-protractor-jasmine/
// https://scotch.io/@charlieoduk/angular-end-to-end-testing507


// --- Karma

// https://scotch.io/tutorials/testing-angular-with-jasmine-and-karma-part-1