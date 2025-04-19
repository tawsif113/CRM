TESTING?



# CRM MVP

A Minimum Viable Product (MVP) for a Customer Relationship Management (CRM) system designed to manage customer interactions, sales pipelines, and basic workflows for small to medium-sized businesses.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Overview
This CRM MVP provides core functionality for managing customer relationships and sales processes. It includes essential entities like Users, Contacts, Accounts, Leads, Opportunities, and Activities, enabling businesses to track leads, manage customer data, and monitor sales pipelines. The project is built to be lightweight, extensible, and suitable for early feedback and iteration.

## Features
- **User Management**: Secure user authentication and role-based access.
- **Contact & Account Management**: Store and manage individual and organizational customer data.
- **Lead Tracking**: Capture and qualify potential customers.
- **Opportunity Management**: Track sales deals through various stages.
- **Activity Logging**: Record interactions like calls, emails, and meetings.
- **Basic Workflows**: Automate tasks like lead assignment and follow-up reminders.

## Tech Stack
- **Backend**: Springboot
- **Database**: PostgreSQL
- **Frontend**: ReactJs / NextJs
- **Authentication**: JWT
- **Deployment**: Docker / Vercel / Hostinger / Render


## Usage
1. **Register a User**: Create an account to access the CRM.
2. **Add Contacts/Accounts**: Input customer data manually or import via CSV.
3. **Manage Leads**: Add leads, assign them to users, and track their status.
4. **Track Opportunities**: Create and update sales deals, moving them through stages.
5. **Log Activities**: Record interactions like calls or emails for historical tracking.
6. **View Dashboard**: Monitor key metrics like open leads and pipeline status.

## Database Schema
The CRM uses the following core entities:
- **Users**: UserID, Username, Password, Email, Role
- **Contacts**: ContactID, FirstName, LastName, Email, Phone, AccountID
- **Accounts**: AccountID, AccountName, Industry, BillingAddress
- **Leads**: LeadID, FirstName, LastName, Email, Status, Source
- **Opportunities**: OpportunityID, OpportunityName, Amount, CloseDate, Stage
- **Activities**: TaskID, Subject, DueDate, Status, RelatedTo

## Database Schema will change 

## License
This project is licensed under the [MIT License](LICENSE). See the LICENSE file for details.

