package najah.edu.acceptance;

import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import najah.edu.Registration;

public class loginSteps {
    Registration ob;

    public loginSteps() {
        super();
        ob = new Registration();
    }

    @Given(": The email is {string}")
    public void theEmailIs(String email) {
        ob.setEmail(email);
    }
 
    @Given(": The password is {string}")
    public void thePasswordIs(String password) {
        ob.setPassword(password);
    }

    @Then("Beneficiary User login")
    public void beneficiaryUserLogin() {
        ob.customerIslLogin(ob.getEmail(), ob.getPassword());
        Assert.assertTrue(ob.getCustomerLogin());
    }

    @Then("Beneficiary User can not login")
    public void beneficiaryUserCanNotLogin() {
       
        Assert.assertFalse(ob.getCustomerLogin());
    }

    @Then("Beneficiary User enter false pass then can not login")
    public void beneficiaryUserEnterFalsePassThenCanNotLogin() {
        ob.customerIslLogin(ob.getEmail(), ob.getPassword());
        Assert.assertFalse(ob.getCustomerLogin());
    }

    @Then("Admin can login")
    public void adminCanLogin() {
        ob.AdminLogin(ob.getEmail(), ob.getPassword());
        Assert.assertTrue(ob.getAdminloged());
    }

    @Then("Admin cant login because email wrong")
    public void adminCantLoginBecauseEmailWrong() {
       
        Assert.assertFalse(ob.getAdminloged());
    }

    @Then("Admin cant login because pass wrong")
    public void adminCantLoginBecausePassWrong() {
        ob.AdminLogin(ob.getEmail(), ob.getPassword());
        Assert.assertFalse(ob.getAdminloged());
    }

    @Then("owner can login")
    public void ownerCanLogin() {
        ob.OwnerIsLogin(ob.getEmail(), ob.getPassword());
        Assert.assertTrue(ob.getOwnerLogin());
    }

    @Then("owner can't login")
    public void ownerCanTLogin() {
       
        Assert.assertFalse(ob.getOwnerLogin());
    }

    @Then("owner enter false pass then can't login")
    public void ownerEnterFalsePassThenCanTLogin() {
        ob.OwnerIsLogin(ob.getEmail(), ob.getPassword());
        Assert.assertFalse(ob.getOwnerLogin());
    }
}
