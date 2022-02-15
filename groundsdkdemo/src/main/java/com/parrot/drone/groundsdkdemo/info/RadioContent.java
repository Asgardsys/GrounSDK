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

import androidx.annotation.NonNull;

import com.parrot.drone.groundsdk.device.Drone;
import com.parrot.drone.groundsdk.device.instrument.Radio;
import com.parrot.drone.groundsdkdemo.R;

class RadioContent extends InstrumentContent<Drone, Radio> {

    RadioContent(@NonNull Drone drone) {
        super(R.layout.radio_info, drone, Radio.class);
    }

    @Override
    ViewHolder onCreateViewHolder(@NonNull View rootView) {
        return new ViewHolder(rootView);
    }

    private static final class ViewHolder extends InstrumentContent.ViewHolder<RadioContent, Radio> {

        @NonNull
        private final TextView mRssiText;

        @NonNull
        private final TextView mLinkSignalQualityText;

        @NonNull
        private final TextView mLinkSignalPerturbedText;

        @NonNull
        private final TextView m4GInterferingText;

        ViewHolder(@NonNull View rootView) {
            super(rootView);
            mRssiText = findViewById(R.id.rssi);
            mLinkSignalQualityText = findViewById(R.id.link_signal_quality);
            mLinkSignalPerturbedText = findViewById(R.id.link_signal_perturbed);
            m4GInterferingText = findViewById(R.id.interfering_with_4g);
        }

        @Override
        void onBind(@NonNull RadioContent content, @NonNull Radio radio) {
            mRssiText.setText(mContext.getString(R.string.rssi_format, radio.getRssi()));
            int quality = radio.getLinkSignalQuality();
            mLinkSignalQualityText.setText(quality == -1 ? mContext.getString(R.string.no_value) :
                    mContext.getString(R.string.link_signal_quality, quality));
            mLinkSignalPerturbedText.setText(Boolean.toString(radio.isLinkPerturbed()));
            m4GInterferingText.setText(Boolean.toString(radio.is4GInterfering()));
        }
    }
}