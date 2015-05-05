Story: User configure liabilities
  As a user I would like to configure liabilities
  so this can build accounts payable for each month

Scenario: Creating new liability for accounts payable
Given I am registering a new liability for a supplier
When I type supplier data, due date and average payment value
Then I would like to see this liability within a list ordered by due date
