package com.revature.revabank.models;

public enum AccountType {
    // values declared within enums are constants and are comma separated
    CHECKING("Checking"), SAVINGS("Savings");

    private String accountTypeName;

    // enum constructors are implicitly private
    AccountType(String name) {
        this.accountTypeName = name;
    }

    public static AccountType getByName(String name) {

        for (AccountType accountType : AccountType.values()) {
            if (accountType.accountTypeName.equals(name)) {
                return accountType;
            }
        }

        return CHECKING;

        // functional implementation of the above code
//        return Arrays.stream(Role.values())
//                .filter(role -> role.roleName.equals(name))
//                .findFirst()
//                .orElse(LOCKED);

    }

    @Override
    public String toString() {
        return accountTypeName;
    }

}
