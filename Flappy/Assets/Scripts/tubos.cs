using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class tubos : MonoBehaviour
{

    Transform tr_;

    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Esto es el Start");

        tr_ = GetComponent<Transform>();

        //tr_.position = new Vector3(0.0f, 0.0f, 0.0f);

    }

    // Update is called once per frame
    void Update()
    {
        Debug.Log("Esto es el Update");
        tr_.Translate(-0.001f, 0.0f, 0.0f);
    }
}
