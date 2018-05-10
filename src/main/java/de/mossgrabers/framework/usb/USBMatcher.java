// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2018
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.usb;

import java.util.ArrayList;
import java.util.List;


/**
 * Contains the description for a USB device to claim and the interfaces/endpoints, which are
 * intended to be used.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class USBMatcher
{
    private final short                 vendor;
    private final short                 productID;
    private final List<EndpointMatcher> endpoints = new ArrayList<> ();


    /**
     * Constructor for an USB device matcher.
     *
     * @param vendor The vendor ID
     * @param productID The product ID
     */
    public USBMatcher (final short vendor, final short productID)
    {
        this.vendor = vendor;
        this.productID = productID;
    }


    /**
     * Constructor for an USB device matcher with 1 interface and 1 endpoint.
     *
     * @param vendor The vendor ID
     * @param productID The product ID
     * @param interfaceNumber The interface
     * @param endpointAddress The endpoint
     */
    public USBMatcher (final short vendor, final short productID, final byte interfaceNumber, final byte endpointAddress)
    {
        this (vendor, productID, interfaceNumber, new byte []
        {
            endpointAddress
        });
    }


    /**
     * Constructor for an USB device matcher with 1 interface and multiple endpoints.
     *
     * @param vendor The vendor ID
     * @param productID The product ID
     * @param interfaceNumber The interface
     * @param endpointAddresses The endpoints
     */
    public USBMatcher (final short vendor, final short productID, final byte interfaceNumber, final byte [] endpointAddresses)
    {
        this (vendor, productID);
        this.addEndpoints (interfaceNumber, endpointAddresses);
    }


    /**
     * Get the vendor ID of the USB device
     *
     * @return The vendor ID
     */
    public short getVendor ()
    {
        return this.vendor;
    }


    /**
     * Get the product ID of the USB device
     *
     * @return The product ID
     */
    public short getProductID ()
    {
        return this.productID;
    }


    /**
     * Add multiple endpoints on an interface.
     *
     * @param interfaceNumber The interface
     * @param endpointAddresses The endpoints on the interface
     */
    public final void addEndpoints (final byte interfaceNumber, final byte [] endpointAddresses)
    {
        this.endpoints.add (new EndpointMatcher (interfaceNumber, endpointAddresses));
    }


    /**
     * Add an endpoint.
     *
     * @param interfaceNumber The interface
     * @param endpointAddress The endpoint on the interface
     */
    public final void addEndpoint (final byte interfaceNumber, final byte endpointAddress)
    {
        this.endpoints.add (new EndpointMatcher (interfaceNumber, new byte []
        {
            endpointAddress
        }));
    }


    /**
     * Get the configured endpoints.
     *
     * @return The endpoints
     */
    public List<EndpointMatcher> getEndpoints ()
    {
        return new ArrayList<> (this.endpoints);
    }

    /**
     * One or more endpoints on the USB device.
     */
    public class EndpointMatcher
    {
        private final byte    interfaceNumber;
        private final byte [] endpointAddresses;


        /**
         * Constructor.
         *
         * @param interfaceNumber The interface number
         * @param endpointAddresses The addresses of the endpoints
         */
        public EndpointMatcher (final byte interfaceNumber, final byte [] endpointAddresses)
        {
            this.interfaceNumber = interfaceNumber;
            this.endpointAddresses = endpointAddresses;
        }


        /**
         * Get the interface number.
         *
         * @return The interface number
         */
        public byte getInterfaceNumber ()
        {
            return this.interfaceNumber;
        }


        /**
         * Get the endpoint addresses of the interface.
         *
         * @return The endpoint addresses
         */
        public byte [] getEndpointAddresses ()
        {
            return this.endpointAddresses;
        }
    }
}
