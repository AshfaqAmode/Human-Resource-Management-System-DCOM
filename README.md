## System Description

The proposed Human Resource Management (HRM) System for PENTAFOUR is a user-friendly, scalable, and secure application designed to manage employee leave records efficiently. Built using Java with Remote Method Invocation (RMI) for distributed computing, the system enables seamless communication between employees and HR staff.

The system stores all data in a centralized SQLite database, ensuring easy access, consistency, and reliability.

## Key functionalities include:

Employee Registration: HR staff can register employees with their details, including first name, last name, IC/Passport number, and initial leave balance. Profile Management: Employees can update their profiles and manage family details. Leave Management: Employees can check their leave balance, apply for leave, and view their leave history. HR staff can approve/reject leave applications. Reporting: HR staff can generate yearly leave reports for employees. Secure Communication: All interactions are secured using encrypted RMI communication protocols. The application ensures fault tolerance and supports multiple concurrent users, making it reliable and efficient for organizational use.

## File Structure

HRMSystem/
├── README.md                  # Project overview and instructions
├── .gitignore                 # Git ignore file for excluding unnecessary files
├── src/                       # Source code directory
│   ├── server/                # Server-side implementation
│   │   ├── Server.java        # Main server application
│   │   ├── Calculator.java    # Remote interface for leave calculations (if any)
│   │   ├── HRManager.java     # Server-side methods for HR functions
│   │   ├── DatabaseHelper.java # SQLite database connection and queries
│   │   ├── Employee.java      # Employee model class
│   │   ├── LeaveApplication.java # Leave application model class
│   │   └── utils/             # Utility classes
│   │       ├── EncryptionUtil.java # For encrypting communication
│   │       └── LoggerUtil.java     # For logging activities
│   ├── client/                # Client-side implementation
│   │   ├── Client.java        # Main client application
│   │   ├── EmployeeClient.java # Client-side methods for employee functions
│   │   ├── HRClient.java      # Client-side methods for HR functions
│   │   └── gui/               # GUI components (optional)
│   │       ├── MainMenu.java  # GUI for the main menu
│   │       └── LeaveForm.java # GUI for leave applications
│   └── shared/                # Shared resources between client and server
│       ├── RemoteInterface.java # RMI remote interface
│       ├── Constants.java     # Shared constants
│       └── Utils.java         # Shared utility methods
├── database/                  # Database files
│   ├── hrm.db                 # SQLite database file
│   ├── schema.sql             # SQL file to set up database schema
│   └── seed.sql               # SQL file to seed initial data
├── build/                     # Compiled files (can be excluded from version control)
├── lib/                       # External libraries (if any)
└── scripts/                   # Helper scripts for building and running
    ├── start-rmiregistry.sh   # Shell script to start the RMI registry
    ├── start-server.sh        # Shell script to start the server
    └── start-client.sh        # Shell script to start the client