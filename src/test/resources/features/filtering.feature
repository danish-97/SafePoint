Feature: Filter crimes by crime type, police or user data, arrest made

  Scenario: Filter loaded crimes by crime type
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose the type of crime (theft)
    Then I should only see the chosen type of crime (theft)

  Scenario: Filter loaded crimes by police data
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to display police data
    Then I should only see police data

  Scenario: Filter loaded crimes by user data
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to display user data
    Then I should only see user data

  Scenario: Filter loaded crimes by arrest made
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to filter by arrest made
    Then I should only see crimes where arrests were made

  Scenario: Filter loaded crimes by arrest made and crime type
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to filter by arrest made
    And I choose the type of crime (theft)
    Then I should only see crimes of that type (theft) where arrests were made

  Scenario: Filter loaded crimes by arrest made and user data
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to filter by arrest made
    And  I choose to display user data
    Then I should not see any crimes



