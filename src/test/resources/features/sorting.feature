Feature: Sort crimes according to their frequency, and high risk/low risk areas

  Scenario: Sort crimes from most frequent crimes to least frequent crimes
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to sort by high frequency
    Then the crimes on the sidebar will be sorted based on high frequency

  Scenario: Sort crimes from least frequent to most frequent crimes
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to sort by low frequency
    Then the crimes on the sidebar will be sorted based on low frequency

  Scenario: Sort crimes from high risk areas to low risk areas
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to sort by high risk areas
    Then the crimes on the sidebar will be sorted based on high risk areas

  Scenario: Sort crimes from low risk areas to high risk areas
    Given crimes have been loaded to sidebar and no filters have been selected
    When I choose to sort by low risk areas
    Then the crimes on the sidebar will be sorted based on low risk areas

