Feature: User reported crimes are able to be added, edited and deletedL

  Scenario: Adding a user reported crime
    Given crimes have been loaded to sidebar and no filters have been selected
    When I report a crime
    Then a user reported crime is added to the list of active crimes

