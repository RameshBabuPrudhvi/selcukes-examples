@pdf
Feature: PDF

  Scenario Outline: Verify PDF Text in Browser
    Given User is on Home Page
    And Dictionary PDF link is displayed
    When User clicks the Dictionary PDF link
    Then User should verify Text in Browser
    Examples:
    |Text|
    | Old Icelandic   |

