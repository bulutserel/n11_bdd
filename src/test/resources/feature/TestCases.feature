Feature: N11 Test Cases

  @Login-Logout
  Scenario: Login Logout
    Given the user navigates to home page and verify it
    And the user should click giris yap button
    And the user should navigate login page and verify it
    And the user should enter login credentials
    And the user should logout the account
    And the user should click giris yap button
    And the user enter wrong e-mail
    And the user enter wrong password
    And the user should enter login credentials


  @search-keyword
  Scenario: Search a keyword
    Given the user navigates to home page and verify it
    And the user should click giris yap button
    And the user should navigate login page and verify it
    And the user should enter login credentials
    And the user should search keyword
    And the user display the search results in Browser
    And Include screenshot images from successful