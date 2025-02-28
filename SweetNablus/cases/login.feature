Feature: user login

  Scenario Outline: Beneficiary User can login
    Given : The email is <email>
    And : The password is <password>
    Then Beneficiary User login
    Examples:
      | email                    | password |
      | "s12112317@stu.najah.edu" | "1111"   |

  Scenario Outline: Beneficiary User enter  false Email
    Given : The email is <email>
    And : The password is <password>
    Then Beneficiary User can not login
    Examples:
      | email             | password |
      | "yaso76.com" | "1111"   |

  Scenario Outline: Beneficiary User enter  false Password
    Given : The email is <email>
    And : The password is <password>
    Then Beneficiary User enter false pass then can not login
    Examples:
      | email                    | password   |
      | "yasminnne14@gmail.com" | "12129020" |

  Scenario Outline: Admin can login
    Given : The email is <email>
    And : The password is <password>
    Then Admin can login
    Examples:
      | email                   | password |
      | "yasminnne12@gmail.com"   | "120"     |

  Scenario Outline: Admin enter wrong Email
    Given : The email is <email>
    And : The password is <password>
    Then Admin cant login because email wrong
    Examples:
      | email                     | password |
      | "Samasaad22r@gmail.com" | "120"    |

  Scenario Outline: Admin enter wrong Password
    Given : The email is <email>
    And : The password is <password>
    Then Admin cant login because pass wrong
    Examples:
      | email                   | password |
      | "samasaad12@gmail.com" | "121"    |

  Scenario Outline: owner can login
    Given : The email is <email>
    And : The password is <password>
    Then owner can login
    Examples:
      | email                 | password |
      | "Yara@gmail.com" | "121"  |

  Scenario Outline: owner enter  false Email
    Given : The email is <email>
    And : The password is <password>
    Then owner can't login
    Examples:
      | email              | password |
      | "reem5.com" | "121"  |

  Scenario Outline: owner enter  false Password
    Given : The email is <email>
    And : The password is <password>
    Then owner enter false pass then can't login
    Examples:
      | email                 | password |
      | "reemsalah@gmail.com" | "12129"  |