# project1
Reimbursement-System---Enterprise
----------------------------------------------------------------------------------------------------------------------------------------------------------------
emp_header.html -- carries information reg menu items when employee login to app mgr_header.html -- carries information reg menu items when maanger login to app

emp_homePage.html -- shows image when employee login mgr_homePage.html -- shows image when manager login

login.html -- where user can enter user name and password and click on submit

submitReimbursement.html -- here we enter reimbersementId and status and click on submit

under assets -- we have images for home pages

com.app -- we have servlets below are used for employee login LoginSrv -- this receives userName and password from login.html and pass these to JdbcOPerations-validateLogin method -- this method executes query on database and return whether usernamae and password are valid or invalid if invalid -- we load same loginhtml sahying Invalid credentials if valid -- we get respective user info from database and store in HttpSession object

LogoutSrv -- we logout from app and load login.html PendingReimbursementsSrv -- will receive request from browser and hit database to get all pending reimbursemes infor using JdbcOperations.viewReimbursements method through passing status as pending

ResolvedReimbursementSrv -- will receive request from browser and hit database to get all reimbursements from database through JdbcOPerations.viewReimbursements method through apssing status as not pending

SubmitReimbursementSrv -- this will receive request from browser and pass reimbursemsent info to databse and sore in databse thorugh JdbcOPerations.submitReimbursement

UpdateProfileSrv -- will receive updatd info from browser and store in database through jdbcOperations.updateUser

below are used for manager login ReviewReimbursementSrv -- will receive request from submitReimbursement.html and through JdbcOPerations. updateStatus method we update record in database

ViewAllReimbursemesntsSrv -- will receive request from browser and load all reimbursements info from database here we can enter employee id and status to filter the data for that we use method JdbcOPerations.viewReimbursements to load data based on reqeusted info from browser

ViewEmployeeSrv -- will load all employees info from database through JdbcOPerations.getAllEmployees RegisterEmployeeSrv -- will insert record in database thorugh JdbcOPerations.

com.app.pojo -- here we have entity classes -- User.java -- using this class we carry information regarding user wherever needed in app ex : loggedinuser, registeremployee -- Reimbursement.java -- using this call we carry information regarding reimbursements ex : pendingreimbursements, resolvedreimbursements

com.app.jdbc -- JdbcOperations -- All database queires we are executing from here
we written one common mehtod -- establish connection -- where we provide username and password to connect with database -- EmailSender.java -- this program sends email to given receiver

Technologies used for this project
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
•Java 1.8 •Servlets •JDBC •SQL •PL/SQL •HTML/CSS •Bootstrap •Java Mail •Log4jEnvironment •Tomcat •Postgres Database
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

Expense Reimbursement System (ERS)In this document, all requirements and required technologies pertaining the first full stack individual project of your training will be presented. All requirements are mandatory.

-The Expense Reimbursement System will manage the process of reimbursing employees for expenses incurred while on company time.

All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests.

Finance managers can log in and view all reimbursement requests and history for all employees in the company.

Finance managers are authorized to approve and deny requests for expense reimbursement.

Mandatory Requirements
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
An Employee can: •Login.
•View the employee homepage.
•Logout.
•Submit a reimbursement request.
•View their pending reimbursement requests. •View their resolved reimbursement requests. •Viewtheir information. •Update their information.

A Manager can: •Login. •View the manager home page. •Logout. •Approve/Deny pending reimbursement requests. •View all pending requests of all employees. •View all resolved requests of all employees. •View reimbursement requests of a specific employee. •View all employees.

Optional Requirements
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
An Employeecan: •Upload an image of his/her receipt as part of the reimbursement request. •Receive an email when one of their reimbursement requests is resolved. •Reset their password.

A Manager can: •View animage of the receipt of a reimbursement request. •Register an employee and send an email to that employee with his credentials.

Technologies
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
•Java 1.8 •Servlets •JDBC •SQL  •HTML/CSS •Bootstrap •Java Mail •Log4jEnvironment •Tomcat •Mysql Database
