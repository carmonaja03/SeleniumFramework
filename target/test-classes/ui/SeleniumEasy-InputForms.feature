@seleniumeasy
Feature: Selenium Easy website

  Scenario: Open Selenium Easy website and enter a message in Single Input Field
    Given I open Selenium Easy website
    When I click Input Forms in Navigation menu
    And I click Simple Form Demo in Sub menu
    And I enter message Hello World in Single Input Field
    Then I validate that Your Message displays Hello World