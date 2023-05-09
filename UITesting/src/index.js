const {Builder, By, until} = require('selenium-webdriver')
const assert = require('assert')

const PAGE_URL_TEST = 'http://localhost:8081/#/main'
let driver

const testCase = async (checker) => {
    driver = await new Builder().forBrowser('chrome').build()
    if (driver) {
        await driver.get(PAGE_URL_TEST)
        checker()
        await driver.quit()
    }

}
// const continueReadingButton = await driver
//         .findElement(By
//             .xpath('//*[@id="mainContent"]/div[3]/div[1]/div/div/div/div/div/div[2]/div/div[2]/div/div/div[2]/div[1]/div/div/div/span/div/div/div/button'))
//
// continueReadingButton.click()
//
// let viewsCountText = await driver.wait(until.elementLocated(By.xpath('//*[@id="mainContent"]/div[3]/div[1]/div/div/div/div/div/div[2]/div/div[3]/div/span/span[2]')),10000);
//
// let textDataParsed = await viewsCountText.getText()
//
// assert.equal(textDataParsed.split(' ')[1],'views')


/** TEST CASES */

/** User registers then authorizes */
const register = async () => {
    // const registrationButton = await driver.findElement(By.xpath(''))
}

testCase(register)