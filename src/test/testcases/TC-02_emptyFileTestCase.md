# TC-02: Verify application behavior for an empty file entry
  ____ 
   |  |  |
   | --------------- | --------------- |
   | **priority: P1** | **assignee: avaninetti** |
   | **type: Functional** | **reporter: avaninetti** |
   | **version: 1.0v** | **Automation Status: Automated** |
   | **component: Entropy** | **labels: negative_scenario, entropy** |
   
   
### Description
  Verify application cannot calculate the file entropy for an empty file entry since the default block size calculation is 1024 bytes.
   
### Test steps
    1. Create an empty file
    2. Execute the entropy calculation using the created empty file entry
    
    # Expected result
      Informative error message saying that entropy cannot be calculated because of the minimum bytes file restriction violation
