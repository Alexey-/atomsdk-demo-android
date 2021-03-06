/*
 * Copyright (c) 2018 Atom SDK Demo.
 * All rights reserved.
 */

package com.atom.vpn.demo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.atom.sdk.android.AtomManager;
import com.atom.sdk.android.VPNProperties;
import com.atom.vpn.demo.AtomDemoApplicationController;
import com.atom.vpn.demo.R;
import com.atom.vpn.demo.common.base.BaseSampleActivity;
import com.atom.vpn.demo.fragment.MainFragment;

public class SampleMainActivity extends BaseSampleActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            MainFragment fragment = new MainFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (AtomDemoApplicationController.getInstance().getAtomManager() != null) {
            if (AtomDemoApplicationController.getInstance().getAtomManager().getCurrentVpnStatus(this).equalsIgnoreCase(AtomManager.VPNStatus.CONNECTED)) {
                AtomDemoApplicationController.getInstance().getAtomManager().disconnect(this);

            } else if (AtomDemoApplicationController.getInstance().getAtomManager().getCurrentVpnStatus(this).equalsIgnoreCase(AtomManager.VPNStatus.CONNECTING)) {
                AtomDemoApplicationController.getInstance().getAtomManager().cancel(this);
            }
        }

        super.onBackPressed();
    }
}
