package com.viator42.ugo.module.address;

import com.viator42.ugo.model.Address;
import com.viator42.ugo.module.address.param.LoadParam;

import java.util.ArrayList;

public interface AddressContract {
    interface View {
        void list(ArrayList<Address> addresses);
    }

    interface Presenter {
        void load(LoadParam loadParam);
    }
}
