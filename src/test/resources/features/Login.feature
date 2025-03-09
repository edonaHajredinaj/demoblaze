@login
Feature: Login Functionality

  @login
  Scenario Outline: Successful login with a registered account
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see welcome "<message>"
    And the user logs out

    Examples:
      | username | password | message         |
#      | edonana   | 123123    | Welcome edonana  |
      | edonusa  | 12       | Welcome edonusa |

  @login
  Scenario Outline: Successful login with a username containing spaces
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see welcome "<message>"
    And the user logs out

    Examples:
      | username        | password | message                 |
      | edona test user | 111      | Welcome edona test user |

  @login
  Scenario Outline: Unsuccessful login with incorrect password
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters an incorrect "<password>"
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | username | password | error           |
      | edonana  | 0u8      | Wrong password. |

  @login
  Scenario Outline: Unsuccessful login with an unregistered username
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | username    | password | error                |
      | nonexistent | pass123  | User does not exist. |

  @login
  Scenario Outline: Unsuccessful login with empty username but valid password
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user leaves the username field empty
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | password | error                                  |
      | test123  | Please fill out Username and Password. |

  @login
  Scenario Outline: Unsuccessful login with valid username but empty password
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user leaves the password field empty
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | username | error                                  |
      | edonana  | Please fill out Username and Password. |

  @login
  Scenario Outline: Unsuccessful login with special characters
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | username | password | error                |
      | user@#$% | pass@#$% | User does not exist. |
#    | <script>      | alert(1)     | User does not exist. | Users exists
#    | admin'--      | pass'--      | User does not exist. | Users exists

  @login
  Scenario Outline: Unsuccessful login with very long credentials
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | username                                                                | password                                                                | error                |
      | thisIsAVeryLongUsernameThatExceedsTheNormalLengthOfUsernamesInTheSystem | thisIsAVeryLongPasswordThatExceedsTheNormalLengthOfPasswordsInTheSystem | User does not exist. |
#  Example is not long enough to cause crash (1501 char)

  @login
  Scenario Outline: Unsuccessful login with SQL injection patterns
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user enters the "<username>" username
    And the user enters the "<password>" password
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal

    Examples:
      | username                           | password                         | error                |
#      | admin' OR '1'='1                   | pass' OR '1'='1                   | User does not exist. |
#      | admin'; DROP TABLE users; --       | pass'; DROP TABLE passwords; --   | User does not exist. |
      | admin' UNION SELECT * FROM users-- | pass' UNION SELECT * FROM auth-- | User does not exist. |

  @login
  Scenario Outline: Unsuccessful login with empty username and password
    Given the user opens the website
    And the user is on the "Home" page
    And the user clicks on the "Log in" text
    And the user leaves the username field empty
    And the user leaves the password field empty
    And the user clicks the "Log in" button
    Then the user should see "<error>" on alert popup
    And the user should remain on the login popup
    And the user clicks the "Close" button on the login modal
    Examples:
      | error                                  |
      | Please fill out Username and Password. |