# TC-01: Verify the application calculate the Entropy by default 
  ____ 
   |  |  |
   | --------------- | --------------- |
   | **priority: P1** | **assignee: avaninetti** |
   | **type: Functional** | **reporter: avaninetti** |
   | **version: 1.0v** | **Automation Status: Automated** |
   | **component: Entropy** | **labels: positive_scenario, entropy** |
   
   
### Description
  Verify application calculates the entropy file configured by default if no custom parameter block size assigned.
   
### Test steps
    1. Create a file with minimum of 1024 bytes
    2. Execute the entropy calculation using the created file entry
    
    # Expected result
      Entropy is correctly calculated for a 1024 file segments
