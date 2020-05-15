
Feature: Login

  Scenario: Login Failure
    Given User is on Home Page
    #And Sign In link is displayed
    #When User clicks the Sign In link
    And User enters Credentials to login
      | Username | Password  |
      | user1    | password1 |
      | user2    | password2 |
    #Then User should be login failed message

@book
  Scenario: Book Store scenario
    Given Java is my favorite book
    Then Books are defined by json
   """json
  {
     "Name": "Java",
     "Company": "Selcukes",
     "Location": "India"
  }
  """
