package stanevich.elizaveta.spbseeker.travel.ar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.ar.core.Config
import com.google.ar.core.Plane
import com.google.ar.core.Session
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.MaterialFactory
import com.google.ar.sceneform.rendering.ShapeFactory
import com.google.ar.sceneform.rendering.Texture
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class ARRestoreFragment : ArFragment() {

    private val anchorsList = ArrayList<AnchorNode>()

    override fun getSessionConfiguration(session: Session?): Config {
        val config = Config(session)
        config.planeFindingMode = Config.PlaneFindingMode.VERTICAL
        return config
    }


    private var currentAnchor: AnchorNode? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupTapListener()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setupTapListener() {
        setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)

            Toast.makeText(
                this.activity,
                "Нужно ещё ${4 - anchorsList.size}",
                Toast.LENGTH_SHORT
            ).show()

            anchorNode.setParent(arSceneView.scene)

            if (plane.type == Plane.Type.VERTICAL) {
                anchorsList.add(anchorNode)
            }

            if (anchorsList.size == 6) {
                currentAnchor!!.anchor!!.detach()
                anchorsList.clear()
            } else if (anchorsList.size == 5) {
                val sampler = Texture.Sampler.builder()
                    .setMinFilter(Texture.Sampler.MinFilter.LINEAR_MIPMAP_LINEAR)
                    .setMagFilter(Texture.Sampler.MagFilter.LINEAR)
                    .setWrapModeR(Texture.Sampler.WrapMode.REPEAT)
                    .setWrapModeS(Texture.Sampler.WrapMode.REPEAT)
                    .setWrapModeT(Texture.Sampler.WrapMode.REPEAT)
                    .build()

                Texture.builder()
                    .setSource { this.activity?.assets?.open("p.jpg") }
                    .setSampler(sampler)
                    .build()
                    .thenAccept { texture ->
                        onTextureBuilt(texture, anchorNode)
                    }
            }
        }
    }

    private fun onTextureBuilt(
        texture: Texture?,
        anchorNode: AnchorNode
    ) {
        val leftToRight = Vector3.subtract(
            anchorsList[0].worldPosition,
            anchorsList[1].worldPosition
        )
        val bottomToTop = Vector3.subtract(
            anchorsList[2].worldPosition,
            anchorsList[3].worldPosition
        )

        MaterialFactory.makeOpaqueWithTexture(this.activity, texture)
            .thenAccept { material ->

                val model = ShapeFactory.makeCube(
                    Vector3(leftToRight.length(), 0.001f, bottomToTop.length()),
                    Vector3.zero(), material
                )

                currentAnchor = anchorNode

                val node = TransformableNode(transformationSystem)

                node.localRotation = Quaternion.axisAngle(Vector3.up(), 0f)
                node.setParent(currentAnchor)
                node.renderable = model
            }
    }
}