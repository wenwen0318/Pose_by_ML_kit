/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.posebymlkit.objectdetector;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.example.posebymlkit.GraphicOverlay;
import com.example.posebymlkit.VisionProcessorBase;
import com.google.mlkit.vision.objects.DetectedObject;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.example.posebymlkit.objectdetector.ObjectDetectorProcessor;
import com.google.mlkit.vision.objects.ObjectDetectorOptionsBase;
import com.example.posebymlkit.CameraSource;
import com.example.posebymlkit.CameraSourcePreview;
import java.util.List;

/** A processor to run object detector. */
public class ObjectDetectorProcessor extends VisionProcessorBase<List<DetectedObject>> {

    private static final String TAG = "ObjectDetectorProcessor";

    private final ObjectDetector detector;
    public CameraSource cameraSource = null;

    Context context;
    GraphicOverlay graphicOverlay;
    CameraSourcePreview preview;
    String cardView;
    int userLevel;

    public ObjectDetectorProcessor(Context context, ObjectDetectorOptionsBase options,CameraSource cameraSource, GraphicOverlay graphicOverlay, CameraSourcePreview preview,
                                   String cardView, int userLevel) {
        super(context);
        this.context = context;
        this.graphicOverlay = graphicOverlay;
        this.preview = preview;
        this.cameraSource = cameraSource;
        this.cardView = cardView;
        this.userLevel = userLevel;
        detector = ObjectDetection.getClient(options);
    }

    @Override
    public void stop() {
        super.stop();
        detector.close();
    }

    @Override
    protected Task<List<DetectedObject>> detectInImage(InputImage image) {
        return detector.process(image);
    }

    @Override
    protected void onSuccess(
            @NonNull List<DetectedObject> results, @NonNull GraphicOverlay graphicOverlay) {
        for (DetectedObject object : results) {
            for (DetectedObject.Label label : object.getLabels()) {
                if (label.getText().equals("Person")) {
                    new ObjectDetectorToPoseDetector(context, cameraSource, graphicOverlay, preview, cardView, userLevel);
                }
            }
            graphicOverlay.add(new ObjectGraphic(graphicOverlay, object));
        }
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Object detection failed!", e);
    }
}
