const {Builder, By, until} = require('selenium-webdriver')
const assert = require('assert')

const PAGE_URL_TEST = 'http://localhost:8081'
let driver

const user = {
    username: 'afa6',
    email: 'afa6@gmail.com',
    password: 'qwerty'
}

let quizPIN
const correctAnswerIdx = 2


const runTestCases = async (...testCases) => {
    driver = await new Builder().forBrowser('chrome').build()
    await driver.get(PAGE_URL_TEST)
    for (let i = 0; i < testCases.length; i++) {
        await testCases[i]();
    }
}

/** TEST CASES */

const register = async () => {
    await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="dashboard"]')), 1000)
    const registrationButton = await driver.findElement(By.xpath('//*[@data-test-id="signUpButton"]'))
    registrationButton.click()

    await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="dialogSignUp"]')), 1000);

    const firstNameInput = await driver.findElement(By.xpath('//*[@data-test-id="firstnameSignUp"]'));
    firstNameInput.sendKeys('Evgeny')

    const lastNameInput = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="lastnameSignUp"]')), 1000);
    lastNameInput.sendKeys('Afanasyev')

    const emailInput = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="emailSignUp"]')), 1000);
    emailInput.sendKeys(user.email)

    const usernameInput = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="usernameSignUp"]')), 1000);
    usernameInput.sendKeys(user.username)

    const passwordInput = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="passwordSignUp"]')), 1000);
    passwordInput.sendKeys(user.password)

    const confirmPasswordInput = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="confirmPasswordSignUp"]')), 1000);
    confirmPasswordInput.sendKeys(user.password)

    const confirmSignUpButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="confirmSignUp"]')), 1000);
    confirmSignUpButton.click()
}

const login = async () => {
    await driver.sleep(2000)
    const signInButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="signInButton"]')), 1000);
    signInButton.click()

    await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="dialogSignIn"]')), 1000);

    const usernameInputLogin = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="usernameSignIn"]')), 1000);
    usernameInputLogin.sendKeys(user.username)

    const passwordInputLogin = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="passwordSignIn"]')), 1000);
    passwordInputLogin.sendKeys(user.password)

    const confirmSignInButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="confirmSignIn"]')), 1000);
    confirmSignInButton.click()
}

const initQuiz = async () => {
    const quizName = 'TestQuiz1'
    await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="userHomeName"]')), 1000);

    const quizNameInput = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="createQuiz"]')), 1000);
    quizNameInput.sendKeys(quizName)

    const confirmQuizCreateButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="createQuizConfirm"]')), 1000);
    confirmQuizCreateButton.click()

    const createdQuizEl = await driver.wait(until.elementLocated(By.xpath(`//div[text()="${quizName}"]`)), 1000);
    const createdQuizName = await createdQuizEl.getText()
    assert.equal(createdQuizName,quizName)
    createdQuizEl.click()

    await driver.sleep(2000)
}

const fillQuizWithOneQuestion = async () => {
    const quizPINEl = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="quizPIN"]')), 1000);
    const quizPINItems = await quizPINEl.getText()
    const quizPINItemsSplitted = quizPINItems.split(' ')
    quizPIN = quizPINItemsSplitted[quizPINItemsSplitted.length-1]

    const createQuestionSectionButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="createQuestionSection"]')), 1000);
    createQuestionSectionButton.click()

    const editQuestionButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="editQuestion"]')), 1000);
    editQuestionButton.click()

    const questionNameEdit = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="questionNameEdit"]')), 1000);
    questionNameEdit.sendKeys('Question 2')
    const answersFormInputs = await driver.findElements(By.xpath('//*[@data-test-id="quizAnswerEditable"]'));

    for (let idx in answersFormInputs) {
        answersFormInputs[idx].sendKeys(`Answer ${idx}`)
    }

    const answersFormCheckboxes = await driver.findElements(By.xpath('//*[@data-test-id="quizAnswerCheckbox"]'));
    answersFormCheckboxes[correctAnswerIdx].click()

    const acceptChangesButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="acceptChanges"]')), 1000);
    acceptChangesButton.click()

    const exitQuestionButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="exitQuestion"]')), 1000);
    exitQuestionButton.click()

    await driver.sleep(2000)
}

const logout = async () => {
    const logoutButton = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="logoutFromUser"]')), 1000);
    logoutButton.click()
}

const passQuiz = async () => {
    await driver.sleep(1000)
    const enterPIN = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="enterPIN"]')));
    enterPIN.sendKeys(quizPIN)
    const enterPINSubmit = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="enterPINSubmit"]')), 1000);
    enterPINSubmit.click()

    await driver.sleep(2000)

    const enterName = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="enterNameToPass"]')));
    enterName.sendKeys('Maxim')
    const enterNameSubmit = await driver.wait(until.elementLocated(By.xpath('//*[@data-test-id="enterNameToPassConfirm"]')), 1000);
    enterNameSubmit.click()

    await driver.sleep(3000)

    const answers = await driver.findElements(By.xpath('//*[@data-test-id="quizGameQuestion"]'));
    answers[correctAnswerIdx].click()

    await driver.sleep(2000)
    assert.doesNotThrow(() => {
        driver.wait(until.elementLocated(By.xpath(`//div[text()="The quiz is finished !"]`)), 1000);
    });

}

runTestCases(
    register,
    login,
    initQuiz,
    fillQuizWithOneQuestion,
    logout,
    passQuiz
)