/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.project.workgroup.partyplay.views;

import android.widget.ImageView;

public interface RecyclerClickListener {
    void onElementClick(int position, ImageView characterImageView);
}
