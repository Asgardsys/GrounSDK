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

package com.parrot.drone.groundsdk.internal.device.pilotingitf.animation;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import com.parrot.drone.groundsdk.device.pilotingitf.animation.Animation;
import com.parrot.drone.groundsdk.device.pilotingitf.animation.Dronie;

/**
 * Implementation class for the {@code Dronie} animation.
 */
public class DronieCore extends AnimationCore implements Dronie {

    /** Animation speed, in meters per second. */
    @FloatRange(from = 0)
    private final double mSpeed;

    /** Animation distance, in meters. */
    @FloatRange(from = 0)
    private final double mDistance;

    /** Animation execution mode. */
    @NonNull
    private final Mode mMode;

    /**
     * Constructor.
     *
     * @param speed    animation speed, in meters per second
     * @param distance animation distance, in meters
     * @param mode     animation execution mode
     */
    public DronieCore(@FloatRange(from = 0) double speed, @FloatRange(from = 0) double distance, @NonNull Mode mode) {
        super(Type.DRONIE);
        mSpeed = speed;
        mDistance = distance;
        mMode = mode;
    }

    @Override
    public double getSpeed() {
        return mSpeed;
    }

    @Override
    public double getDistance() {
        return mDistance;
    }

    @NonNull
    @Override
    public Mode getMode() {
        return mMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        DronieCore that = (DronieCore) o;

        return Double.compare(that.mSpeed, mSpeed) == 0
               && Double.compare(that.mDistance, mDistance) == 0
               && mMode == that.mMode;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(mSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(mDistance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + mMode.hashCode();
        return result;
    }

    @Override
    public boolean matchesConfig(@NonNull Animation.Config config) {
        if (config.getAnimationType() == Type.DRONIE) {
            Dronie.Config dronieConfig = (Dronie.Config) config;
            return (!dronieConfig.usesCustomSpeed()
                    || almostEqual(dronieConfig.getSpeed(), mSpeed))
                   && (!dronieConfig.usesCustomDistance()
                       || almostEqual(dronieConfig.getDistance(), mDistance))
                   && (dronieConfig.getMode() == null
                       || dronieConfig.getMode() == mMode);
        }
        return false;
    }
}
