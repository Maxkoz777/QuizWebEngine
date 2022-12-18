export const validatePasswordsSimilarity = (password, confirmPassword) => {
  for (let i = 0; i < confirmPassword.length; i++) {
    if (confirmPassword[i] !== password[i]) {
      return false;
    }
  }
  return true;
};
