/*
 * WorldGuard, a suite of tools for Minecraft
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldGuard team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldguard.util.paste;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pastebin implements Paster {

    private static final Pattern URL_PATTERN = Pattern.compile("https?://pastebin.com/([^/]+)$");

    private boolean mungingLinks = true;

    public boolean isMungingLinks() {
        return mungingLinks;
    }

    public void setMungingLinks(boolean mungingLinks) {
        this.mungingLinks = mungingLinks;
    }

    @Override
    public ListenableFuture<URL> paste(String content) {
        if (mungingLinks) {
            content = content.replaceAll("http://", "http_//");
        }

        return Pasters.getExecutor().submit(new PasteTask(content));
    }

    private final class PasteTask implements Callable<URL> {
        private final String content;

        private PasteTask(String content) {
            this.content = content;
        }

        @Override
        public URL call() throws IOException, InterruptedException {
            throw new IllegalStateException("Please make pastes yourself - Solar"); // Solar
        }
    }
    
}
