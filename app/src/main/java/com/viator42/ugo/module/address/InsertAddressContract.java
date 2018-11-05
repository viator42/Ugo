package com.viator42.ugo.module.address;

import com.viator42.ugo.model.Address;
import com.viator42.ugo.module.address.param.DeleteAddressParam;
import com.viator42.ugo.module.address.param.InsertAddressParam;
import com.viator42.ugo.module.address.param.LoadParam;
import com.viator42.ugo.module.address.result.InsertAddressResult;

import java.util.ArrayList;

public interface InsertAddressContract {
    interface View {
        void insertSuccess(InsertAddressResult insertAddressResult);
    }

    interface Presenter {
        void add(InsertAddressParam insertAddressParam);
        void update(InsertAddressParam insertAddressParam);
        void delete(DeleteAddressParam deleteAddressParam);
    }
}
