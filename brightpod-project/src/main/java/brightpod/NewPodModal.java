package brightpod;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewPodModal extends BasePage {
    private String newPodName;

    public NewPodModal() {
//        this.newPodName = newPodName;
    }

    @Override
    protected void waitUntilPageObjectIsLoaded() {

    }

    @FindBy(how= How.LINK_TEXT, using="Create a New Pod")
    WebElement newPodButton;
    @FindBy(how= How.LINK_TEXT, using="Create a Blank Pod")
    WebElement blankPodButton;
    @FindBy(how= How.XPATH, using="//input[@type='text'][@id='project-name']")
    WebElement projectNameTextBox;
    @FindBy(how= How.XPATH, using="//button[@class='btn btn-success btn-primary new-project-button']")
    WebElement createPodAndInvitePeopleButton;

//    newPodButton.click();
//    blankPodButton.click();
//    projectNameTextBox.sendKeys(newPodName);
//    createPodAndInvitePeopleButton.click();

//    @Override
//    public void executeAction() {

//    }
}