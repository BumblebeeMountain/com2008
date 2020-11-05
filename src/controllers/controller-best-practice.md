# Controller Best Practice

---

### Create functions

* First of all, check that a record doesn't already exist using the get (single) function in the same file. If there is a record throw an `ExistingRecordException`.
* Perform the creation with the SQL commands
* If there was a database issue throw a `GeneralProcessingException`.
* The function will likely be void so nothing else to do

---

### Get functions (single)

* Perform the SQL select function - there should only be one record - but you will still have to select `[0]` from the returned elements.
* If there isn't anything in the list - you will have to throw a `NoRecordException` as clearly some of the parameters were wrong.
* If there was a database issue throw a `GeneralProcessingException`.
* If we do have a record, create a new model object of the type you want to return by manipulating the result set. 

---

### Get functions (array)

* Exactly the same as the single get, except this time you will have to make an array of model objects rather than just selecting the first element.
* **Please note**: Array gets do not throw a NoRecordException even if the array is empty - because this may be expected behaviour - eg we haven't made any degrees yet.  
