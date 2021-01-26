package com.braintreepayments.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Builder used to construct an local payment request.
 */
public class LocalPaymentRequest {
    private static final String INTENT_KEY = "intent";
    private static final String RETURN_URL_KEY = "returnUrl";
    private static final String CANCEL_URL_KEY = "cancelUrl";
    private static final String EXPERIENCE_PROFILE_KEY = "experienceProfile";
    private static final String NO_SHIPPING_KEY = "noShipping";
    private static final String FUNDING_SOURCE_KEY = "fundingSource";
    private static final String AMOUNT_KEY = "amount";
    private static final String CURRENCY_CODE_KEY = "currencyIsoCode";
    private static final String GIVEN_NAME_KEY = "firstName";
    private static final String SURNAME_KEY = "lastName";
    private static final String EMAIL_KEY = "payerEmail";
    private static final String PHONE_KEY = "phone";
    private static final String STREET_ADDRESS_KEY = "line1";
    private static final String EXTENDED_ADDRESS_KEY = "line2";
    private static final String LOCALITY_KEY = "city";
    private static final String REGION_KEY = "state";
    private static final String POSTAL_CODE_KEY = "postalCode";
    private static final String COUNTRY_CODE_KEY = "countryCode";
    private static final String MERCHANT_ACCOUNT_ID_KEY = "merchantAccountId";
    private static final String PAYMENT_TYPE_COUNTRY_CODE_KEY = "paymentTypeCountryCode";
    private static final String BIC_KEY = "bic";

    private PostalAddress mAddress;
    private String mAmount;
    private String mCurrencyCode;
    private String mEmail;
    private String mGivenName;
    private String mMerchantAccountId;
    private String mPaymentType;
    private String mPaymentTypeCountryCode;
    private String mPhone;
    private boolean mShippingAddressRequired;
    private String mSurname;
    private String mBankIdentificationCode;

    /**
     * @param address Optional - The address of the customer. An error will occur if this address is not valid.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest address(PostalAddress address) {
        mAddress = address;
        return this;
    }

    /**
     * @param amount Optional - The amount for the transaction.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest amount(String amount) {
        mAmount = amount;
        return this;
    }

    /**
     * @param bankIdentificationCode Optional - the Bank Identification Code of the customer (specific to iDEAL transactions).
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest bic(String bankIdentificationCode) {
        mBankIdentificationCode = bankIdentificationCode;
        return this;
    }


    /**
     * @param currencyCode Optional - A valid ISO currency code to use for the transaction. Defaults to merchant
     * currency code if not set.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest currencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
        return this;
    }

    /**
     * @param email Optional - Payer email of the customer.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest email(String email) {
        mEmail = email;
        return this;
    }

    /**
     * @param givenName Optional - Given (first) name of the customer.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest givenName(String givenName) {
        mGivenName = givenName;
        return this;
    }

    /**
     * @param merchantAccountId Optional - A non-default merchant account to use for tokenization.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest merchantAccountId(String merchantAccountId) {
        mMerchantAccountId = merchantAccountId;
        return this;
    }

    /**
     * @param paymentType - The type of payment
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest paymentType(String paymentType) {
        mPaymentType = paymentType;
        return this;
    }

    /**
     * @param paymentTypeCountryCode The country code of the local payment. This value must be one of
     *                               the supported country codes for a given local payment type listed.
     *                               For local payments supported in multiple countries, this value
     *                               may determine which banks are presented to the customer.
     *                               @see <a href=https://developers.braintreepayments.com/guides/local-payment-methods/client-side-custom/android/v3#invoke-payment-flow>Supported Country Codes</a>
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest paymentTypeCountryCode(String paymentTypeCountryCode) {
        mPaymentTypeCountryCode = paymentTypeCountryCode;
        return this;
    }

    /**
     * @param phone Optional - Phone number of the customer.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest phone(String phone) {
        mPhone = phone;
        return this;
    }

    /**
     * @param shippingAddressRequired - Indicates whether or not the payment needs to be shipped. For digital goods,
     *                                this should be false. Defaults to false.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest shippingAddressRequired(boolean shippingAddressRequired) {
        mShippingAddressRequired = shippingAddressRequired;
        return this;
    }

    /**
     * @param surname Optional - Surname (last name) of the customer.
     * @return {@link LocalPaymentRequest}
     */
    public LocalPaymentRequest surname(String surname) {
        mSurname = surname;
        return this;
    }

    public PostalAddress getAddress() {
        return mAddress;
    }

    public String getAmount() {
        return mAmount;
    }

    public String getBic() {
        return mBankIdentificationCode;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getGivenName() {
        return mGivenName;
    }

    public String getMerchantAccountId() {
        return mMerchantAccountId;
    }

    public String getPaymentType() {
        return mPaymentType;
    }

    public String getPaymentTypeCountryCode() {
        return mPaymentTypeCountryCode;
    }

    public String getPhone() {
        return mPhone;
    }

    public boolean getShippingAddressRequired() {
        return mShippingAddressRequired;
    }

    public String getSurname() {
        return mSurname;
    }

    public String build(String returnUrl, String cancelUrl) {
        try {
            JSONObject payload = new JSONObject()
                    .put(INTENT_KEY, "sale")
                    .put(RETURN_URL_KEY, returnUrl)
                    .put(CANCEL_URL_KEY, cancelUrl)
                    .put(FUNDING_SOURCE_KEY, mPaymentType)
                    .put(AMOUNT_KEY, mAmount)
                    .put(CURRENCY_CODE_KEY, mCurrencyCode)
                    .put(GIVEN_NAME_KEY, mGivenName)
                    .put(SURNAME_KEY, mSurname)
                    .put(EMAIL_KEY, mEmail)
                    .put(PHONE_KEY, mPhone)
                    .put(MERCHANT_ACCOUNT_ID_KEY, mMerchantAccountId)
                    .putOpt(PAYMENT_TYPE_COUNTRY_CODE_KEY, mPaymentTypeCountryCode)
                    .putOpt(BIC_KEY, mBankIdentificationCode);

            if (mAddress != null) {
                payload.put(STREET_ADDRESS_KEY, mAddress.getStreetAddress())
                        .put(EXTENDED_ADDRESS_KEY, mAddress.getExtendedAddress())
                        .put(LOCALITY_KEY, mAddress.getLocality())
                        .put(REGION_KEY, mAddress.getRegion())
                        .put(POSTAL_CODE_KEY, mAddress.getPostalCode())
                        .put(COUNTRY_CODE_KEY, mAddress.getCountryCodeAlpha2());
            }

            JSONObject experienceProfile = new JSONObject();
            experienceProfile.put(NO_SHIPPING_KEY, !mShippingAddressRequired);
            payload.put(EXPERIENCE_PROFILE_KEY, experienceProfile);

            return payload.toString();
        } catch (JSONException ignored) {}

        return new JSONObject().toString();
    }
}
