package com.wsd_killers.multiagentscheduleplanner_client.Interfaces;

import com.wsd_killers.multiagentscheduleplanner_client.Data.CustomerData;
import com.wsd_killers.multiagentscheduleplanner_client.Exceptions.negativeValueException;


public interface CustomerInterface {
    void setServiceProviderData(CustomerData data) throws negativeValueException;
}
