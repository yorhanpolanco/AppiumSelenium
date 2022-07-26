#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@conversions
Feature: Validate conversions app

  @dimensions
  Scenario Outline: As a user with the unit of measurement conversion application, I want to perform a conversion
    Given user proceeds to open the application
    When the user selects "<conversion>" as the conversion type
    And select "<ounit>" as the source unit of measure 
    And select "<dunit>" as the destination unit of measure
    And insert the "<value>" to convert
    Then the application shows "<result>" as the result of the conversion
    
    Examples: 
      |conversion| ounit | dunit | value | result |
      |Length    | Inch | Foot | 50 | 4.1667 |
      |Length | Foot | Centimeter| 5 | 152.4|
      |Area			|Are   |Acre  |100 |2.4711  |
      |Area    | Square | Rood | 200 | 1.8365 |
      |Volume | Pint | Liter | 5 | 2.8413|
      |Volume	| Cup   | Quart  |51 |11.2184  |
			|Speed	| Seconds per meter | Meter per hour  |56 |64.2857  | 
      |Speed	| Inch per hour | Meter per hour  |20 |0.508  |  

 # @tag2
 # Scenario Outline: Title of your scenario outline
  #  Given I want to write a step with <name>
   # When I check for the <value> in step
    #Then I verify the <status> in step

    #Examples: 
     # | name  | value | status  |
      #| name1 |     5 | success |
     # | name2 |     7 | Fail    |
