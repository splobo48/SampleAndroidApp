package com.example.shaileshlobo.assignmentapp.ui.addresslocation


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import com.example.shaileshlobo.assignmentapp.utils.AppSpecificUtils
import com.example.shaileshlobo.assignmentapp.ui.MainAcitivityViewModel
import com.example.shaileshlobo.assignmentapp.R
import com.example.shaileshlobo.assignmentapp.data.models.UserModel
import com.example.shaileshlobo.assignmentapp.ui.customerlisting.CustomerListingFragment
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import kotlinx.android.synthetic.main.fragment_address_location.*


/**
 * A simple [Fragment] subclass.
 */
class AddressLocationFragment : Fragment() {




    private var customerId: Long? = null
    companion object {

        fun getInstance(userModel: UserModel) : AddressLocationFragment{
            val fragment = AddressLocationFragment()
            val bundle = Bundle();
            bundle.putLong(AddressLocationFragment.KEY_CUSTOMER_ID_TAG,userModel.id)
            fragment.arguments = bundle;
            return fragment
        }

        val KEY_CUSTOMER_ID_TAG: String? = "id";
    }


    private var mContext: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            customerId = arguments!!.getLong(AddressLocationFragment.KEY_CUSTOMER_ID_TAG)
        }
        setHasOptionsMenu(true);
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_address_location, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        Mapbox.getInstance(mContext as AppCompatActivity,
                AppSpecificUtils.apiKey);

        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(mContext as AppCompatActivity).get(MainAcitivityViewModel::class.java)
        val customer = model.getCustomerById(customerId)

        val address = customer?.address?.let { (mContext as AppCompatActivity)
                .getString(R.string.customer_details_row_address_label,
                        AppSpecificUtils.getCompleteAddress(it))
        }

        val gpsCoordLocation = customer?.address?.geo

        tv_address_text_address_location_fragment.text = address

        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync({ mapboxMap ->

                mapboxMap.addMarker(MarkerOptions()
                        .position(LatLng(gpsCoordLocation?.lat!!, gpsCoordLocation.lng))
                        .title(customer.name)
                        .snippet(customer.email))


        })

    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()

       /* // check if Google play services is available
        val code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(mContext)

        if (code != SUCCESS){
            val dialog = GoogleApiAvailability.getInstance().getErrorDialog(mContext as AppCompatActivity
                    ,code,1)
            dialog.show();
        }*/

        mapView?.onResume()

    }


    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView?.onDestroy()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.home_menu_btn -> {

                val supportFM = (mContext as AppCompatActivity).supportFragmentManager
                for (i in 0..supportFM.backStackEntryCount){
                    supportFM.popBackStack()
                }

                supportFM.beginTransaction()
                        .replace(R.id.fragment_container, CustomerListingFragment())
                        .addToBackStack(null)
                        .commit()

                activity?.findViewById<TextView>(R.id.tv_toolbar)?.text =
                        getString(R.string.main_activity_toolbar_title)

                return true
            }
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {

        inflater?.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater)
    }





}
