/*
 *     Copyright (C) 2019 Parrot Drones SAS
 *
 *     Redistribution and use in source and binary forms, with or without
 *     modification, are permitted provided that the following conditions
 *     are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *     * Neither the name of the Parrot Company nor the names
 *       of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written
 *       permission.
 *
 *     THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *     "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *     LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 *     FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *     PARROT COMPANY BE LIABLE FOR ANY DIRECT, INDIRECT,
 *     INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *     BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
 *     OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 *     AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *     OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 *     OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 *     SUCH DAMAGE.
 *
 */

package com.parrot.drone.groundsdkdemo.info;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.parrot.drone.groundsdk.device.Drone;
import com.parrot.drone.groundsdk.device.instrument.FlyingIndicators;
import com.parrot.drone.groundsdkdemo.R;

class FlyingIndicatorsContent extends InstrumentContent<Drone, FlyingIndicators> {

    FlyingIndicatorsContent(@NonNull Drone drone) {
        super(R.layout.flying_indicators_info, drone, FlyingIndicators.class);
    }

    @Override
    ViewHolder onCreateViewHolder(@NonNull View rootView) {
        return new ViewHolder(rootView);
    }

    static class ViewHolder<C extends InstrumentContent<?, FI>, FI extends FlyingIndicators>
            extends InstrumentContent.ViewHolder<C, FI> {

        @NonNull
        private final TextView mStateText;

        @NonNull
        private final TextView mLandedStateText;

        @NonNull
        private final TextView mFlyingStateText;

        ViewHolder(@NonNull View rootView) {
            super(rootView);
            mStateText = findViewById(R.id.state);
            mLandedStateText = findViewById(R.id.landed_state);
            mFlyingStateText = findViewById(R.id.flying_state);
        }

        @CallSuper
        @Override
        void onBind(@NonNull C content, @NonNull FI flyingIndicators) {
            mStateText.setText(flyingIndicators.getState().toString());
            mLandedStateText.setText(flyingIndicators.getLandedState().toString());
            mFlyingStateText.setText(flyingIndicators.getFlyingState().toString());
        }
    }
}
