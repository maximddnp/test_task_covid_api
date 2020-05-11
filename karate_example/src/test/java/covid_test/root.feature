Feature: Basic elasticsearch tests

    Background:
    * url covidUrlBase

    Scenario: Verify covid api is running
        When method GET
        Then status 200
        And match header Content-Type contains 'application/json'

    Scenario: Verify covid api 'allRoute' info contains correct data
        When method GET
        Then status 200
        And match $.allRoute.Name == 'Get All Data'
        And match $.allRoute.Description == 'Returns all data in the system. Warning: this request returns 8MB+ and takes 5+ seconds'
        And match $.allRoute.Path == '/all'

    Scenario: Verify covid api 'countryStatusDayOneLiveRoute' contains info about query params type
        When method GET
        Then status 200
        And match $.countryStatusDayOneLiveRoute.Description contains 'Country must be the country_slug from /countries'

    Scenario: Verify json schema for total/dayone route
        Given url covidUrlBase + '/total/dayone/country/south-africa/status/confirmed'
        When method GET
        Then status 200
        And match each response.[*] contains
            """
            {
                Country: '#string',
                Cases: '#number',
                Status: '#string',
                Date: '#regex \\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z'
             }
            """
