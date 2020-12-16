# bank_atm
Repo for Bank ATM project in CS 611, Object Oriented Principles class at BU, Fall 2020. 
Clients can use the ATM to create accounts, withdraw/deposit/transfer money,
apply for loans, open a 401K retirement plan, and view their transactions. 
The ATM may also be used by a banker to see all users and accounts, as well
as consider loan applications. 

##### Authors:
- Navoneel Ghosh
- Sandra Zhen
- Nathan Lauer

Please feel free to ask us any questions!

##### Compilation Instructions:
To compile, please execute the following steps:
```bash
$ find ./src/main -name "*.java" > sources.txt 
$ javac @sources.txt
```

Note that we have already provided the sources.txt file, but feel free to regenerate it if desired
Further, note that there may be some warnings. These can be ignored.

##### Running the ATM
Once, compiled the program can be run as follows:
```bash
$ java -cp src main.Main
```

##### Usage 
The ATM can be used either as a Client or an Admin. 

To use the ATM as a Client:
- Once started, you'll need to create an account the first time. Click the sign up button, and following the instructions
- After signing up, you'll need to log in.
- After logging in, a main menu will appear, where options for accounts, transactions, and loans will appear.


To use the ATM as an Admin:
- We have provided a default admin account for the purposes of this project. To use it:
- login with username: admin and password: BankPass@611
- Once logged in, an admin menu appears.

##### Architecture
Please see the included pdf for the overview of the architecture. However, 
generally, the repository is split into gui and backend packages, and makes 
extensive use of design patterns, interfaces, and abstract classes to implement all
features.

##### Design Patterns
There are a number of design patterns that are used throughout this repository. 
This section contains a brief description of them.

###### Singleton Pattern:
There are a number of classes that are Singletons. These classes fall into three different cateogries:
- Currencies: Each currency is implemented as a Singleton, as there should only be a single
entity for each currency.
- ExchangeRateTable: There is one class that contains all exchange rates
- CollectionManagers: Each of the collection managers (described in more detail below) is implemented
as a Singleton, as there should only be one manager for each type of collection.

###### Factory Method/Abstract Factory
We use the Factory Method and Abstract Factory design patterns in order to construct
accounts. There is a primary factory called AccountFactory, and multiple factory subtypes
which build an account of the appropriate type. The primary factory invokes the correct
factory subtype depending on the type of Account that should be created. We also provide 
an interface AccountFactorCreator, which every account factory implements.

###### Strategy Pattern
The interest system is implemented using the strategy pattern. Some accounts can earn interest,
and those that do implement the interface InterestEarnable, which specifies that any such
account must be able to provide an InterestEarningExecutor. There are a number of InterestEarningExecutors,
specifically classes that represent interest compounded daily, monthly, and yearly. For any account
that earns interest, the associated factory will attach the correct InterestEarningExecutor in
the strategy pattern fashion. This allows for easy composition between accounts and the interest 
earning system.

##### Users
Class user is an abstract class, with concrete subtypes Client and Admin.

##### Transactions
We provide an abstract class Transaction, and four concrete subtypes. They are:
- Deposit
- Withdraw
- Transfer
- LoanPayment

Each of these concrete subtypes are individually responsible for the semantics of executing 
the specific transaction. The advantage here is that we can easily group all transactions under
one collection.

##### Accounts
We have a multiple level inheritance system for Accounts. It generally goes as follows:
- asdf