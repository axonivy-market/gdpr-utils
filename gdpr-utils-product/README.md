# GDPR Utils

### For the [Data Protection Laws of the World](https://www.dlapiperdataprotection.com), we introduced the GDPR utils.

* A CronJob has been defined as reminding the responsible for data deletion: A task is created with the option to choose a time interval and delete the user-related data in this time interval by activating the **OK** button (we did not implement this step automatically which would implemented with the case - to guarantee that the responsible is aware of this data deletion step).
* A couple of variables define to support define which when data can be deleted: i.e., what time interval is the earliest possible, if cases that have not been finished yet should be included, and some others.

* Handling business cases with deleted data: it is shown that the corresponding tasks are destroyed and data deleted.

![data-deletion-page](images/data-deletion-page.png)

## Demo

### Executing the General Data Protection Regulation process

This section creates dummy data for presenting how the `Financial Data` will be deleted.

* To start the demo, please start `gdpr-utils-demo/1948C6200884AE99/startCreateDummyData.ivp` process.
* Then start the `gdpr-utils/1943EA22591E28D4/startDataDeletion.ivp` process. It will create a case with the name `General Data Protection Regulation process` and a task will be created and assigned to the `GDPR Administrator` role. You can use the `Developer` account to start this task.
* The page will look like:
  
  ![start-data-deletion](images/start-data-deletion.png)

* Then choose the `Financial Year` that you would like to clean up data and press the `Submit` button. One confirm popup will appear to make sure you understand what you do.
* The output of the process will be looked like:
  
  ![data-deletion-page](images/data-deletion-page.png)

### Check the business case details

You also can check the `Business Case Details` of this process later by opening it in the Portal.

![business-details-page](images/business-details-page.png)

## Setup

This util is designed as a CronJob, so basically it will be trigged one time per year and assign tasks to the `GDPR Administrator` role.
You properly can change the time and also the schedule when the job should be triggered by `gdpr.DataDeletionCronJobPattern` variable.
Make sure that you assign your user to the `GDPR Administrator` role then they can see and work on the task.

* The job find the corresponding the ivy cases that match with your config in `gdpr.CustomFieldsInScope` file.
* Then the found ivy case and task will be destroyed.
* For the business data that your application saved to the Database layer, e.g: postgres DB, then you must config:
  * The `gdpr.EntityCustomField` to define which custom field name of the ivy case that saved the entity Id.
    * Type should be `STRING` or `NUMBER`.
  * The `gdpr.PersistenceUnitName` to define the persistence unit name that contained all your entities, then the job can create an EntityManager based on that name, and find the entity by id which is saved in the `gdpr.EntityCustomField` custom field.
    * You can check the config in `<your-project>/config/persistence.xml`

Read the full variable for more details:

```
@variables.yaml@
```

The `CustomFieldsInScope.json` file can be found in `config/variables/gdpr/CustomFieldsInScope.json`

```
    [
        {
            "name" : "LegalEntity", // The axonivy case's custom field name that this job should be focused on
            "type" : "String", // The type of case's custom field, it should be "String" or "Number"
            "value" : "RDE" // The value of case's custom field
        }
    ]
```