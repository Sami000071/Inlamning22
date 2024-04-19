
Feature: Register a new user

  Scenario Outline: create a new account
    Given I am on the registration page using "<browser>"
    Given I have enterd a valid date of birth "<dateofbirth>"
    Given I have enterd a name "<firstname>" and a last name "<lastname>"
    Given I have enterd a valid email address
    Given I have confirmed my email address
    Given I choose a new password "<pass>"
    Given I confirm my password "<confirmpass>"
    Given I agree on "<Terms and Conditions>"
    Given I agree on CODE OF ETHICS AND CONDUCT and confirm Im over 18
    When I click on Confirm and Join
    Then I should receive confirmation or error message based on the "<expectedd>"

    Examples:
      | firstname | lastname | dateofbirth | pass        | confirmpass   | browser | expectedd                                                                 | Terms and Conditions |
      | Sami      | Awal     | 17/7/2001   | 123334455   | 123334455     | edge    | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 | check                |
      | Adam      |          | 17/7/2000   | 234343545   | 234343545     | chrome  | Last Name is required                                                     | check                |
      | Sara      | kalli    | 17/7/1990   | 33545456786 | 3354545678677 | chrome  | Password did not match                                                    | check               |
      | Saraaa    | kalli    | 17/7/1997   | 33545456786 | 33545456786   | chrome  | You must confirm that you have read and accepted our Terms and Conditions | uncheck              |