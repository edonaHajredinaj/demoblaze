Feature: Sign up

#  @signup
#  Scenario Outline: User creates an account successfully
#    Given the user opens the website
#    And the user is on the "Home" page
#    And the user clicks on the "Sign up" text
#    And the user enters the "<username>" username
#    And the user enters the "<password>" password
#    And the user clicks the "Sign up" button
#    Then the user should see "<message>" on alert popup
#
#    Examples:
#      | username | password | message      |
#      | tosi     | 7        | Sign up successful. |
#
#  @signup
#  Scenario Outline: Unsuccessful signup with empty username and password
#    Given the user opens the website
#    And the user is on the "Home" page
#    And the user clicks on the "Sign up" text
#    And the user leaves the username field empty
#    And the user enters the "<password>" password
#    And the user clicks the "Sign up" button
#    Then the user should see "<error>" on alert popup
#    Examples:
#      | password | error                                  |
#      | 2        | Please fill out Username and Password. |
#
#  @signup
#  Scenario Outline: Unsuccessful login with valid username but empty password
#    Given the user opens the website
#    And the user is on the "Home" page
#    And the user clicks on the "Sign up" text
#    And the user enters the "<username>" username
#    And the user leaves the password field empty
#    And the user clicks the "Sign up" button
#    Then the user should see "<error>" on alert popup
#    Examples:
#      | username    | error                                  |
#      | alea        | Please fill out Username and Password. |
#
#  @signup
#  Scenario Outline: User tries to sign up with an already taken username
#    Given the user opens the website
#    And the user is on the "Home" page
#    And the user clicks on the "Sign up" text
#    And the user enters the "<existing_username>" username
#    And the user enters the "<password>" password
#    And the user clicks the "Sign up" button
#    Then the user should see "<error>" on alert popup
#    Examples:
#      | existing_username | password | error                    |
#      | edonana           | 1        | This user already exist. |