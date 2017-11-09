Feature: Application Test

  @Hello
  Scenario: test
    Given the service is alive
    When I call the uri '/about'
    Then I should receive 'Hello from HelloService'


  @Ignore
  Scenario: test ignored
    Given the service is alive
    When I call the uri '/about'
    Then I should receive 'Hello from HelloService'


  @Filtered
  Scenario: test filtered --> ignore
    Given the service is alive
    When I call the uri '/about'
    Then I should receive 'Hello from HelloService'