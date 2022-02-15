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

import com.parrot.drone.groundsdk.device.pilotingitf.animation.Animation;
import com.parrot.drone.groundsdk.device.pilotingitf.animation.Vertigo;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class VertigoTest {

    private Vertigo.Config mAnimConfig;

    private VertigoCore mAnimCore;

    @Before
    public void setUp() {
        mAnimConfig = new Vertigo.Config();
        mAnimCore = new VertigoCore(60, 10.0, Vertigo.FinishAction.UNZOOM, Animation.Mode.ONCE_THEN_MIRRORED);
    }

    @Test
    public void testConfig() {
        assertThat(mAnimConfig.getAnimationType(), is(Animation.Type.VERTIGO));

        assertThat(mAnimConfig.usesCustomDuration(), is(false));
        assertThat(mAnimConfig.usesCustomMaxZoomLevel(), is(false));
        assertThat(mAnimConfig.getFinishAction(), nullValue());
        assertThat(mAnimConfig.getMode(), nullValue());

        assertThat(mAnimConfig.withDuration(60.0), is(mAnimConfig));
        assertThat(mAnimConfig.usesCustomDuration(), is(true));
        assertThat(mAnimConfig.getDuration(), is(60.0));

        assertThat(mAnimConfig.withMaxZoomLevel(10.0), is(mAnimConfig));
        assertThat(mAnimConfig.usesCustomMaxZoomLevel(), is(true));
        assertThat(mAnimConfig.getMaxZoomLevel(), is(10.0));

        assertThat(mAnimConfig.withFinishAction(Vertigo.FinishAction.UNZOOM), is(mAnimConfig));
        assertThat(mAnimConfig.getFinishAction(), is(Vertigo.FinishAction.UNZOOM));

        assertThat(mAnimConfig.withMode(Animation.Mode.ONCE_THEN_MIRRORED), is(mAnimConfig));
        assertThat(mAnimConfig.getMode(), is(Animation.Mode.ONCE_THEN_MIRRORED));
    }

    @Test
    public void testAnimation() {
        assertThat(mAnimCore.getType(), is(Animation.Type.VERTIGO));
        assertThat(mAnimCore.getDuration(), is(60.0));
        assertThat(mAnimCore.getMaxZoomLevel(), is(10.0));
        assertThat(mAnimCore.getFinishAction(), is(Vertigo.FinishAction.UNZOOM));
        assertThat(mAnimCore.getMode(), is(Animation.Mode.ONCE_THEN_MIRRORED));
    }

    @Test
    public void testMatchesConfig() {
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(true));

        mAnimConfig.withDuration(30);
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(false));
        mAnimConfig.withDuration(60);
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(true));

        mAnimConfig.withMaxZoomLevel(5.0);
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(false));
        mAnimConfig.withMaxZoomLevel(10.0);
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(true));

        mAnimConfig.withMode(Animation.Mode.ONCE);
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(false));
        mAnimConfig.withMode(Animation.Mode.ONCE_THEN_MIRRORED);
        assertThat(mAnimCore.matchesConfig(mAnimConfig), is(true));
    }
}
