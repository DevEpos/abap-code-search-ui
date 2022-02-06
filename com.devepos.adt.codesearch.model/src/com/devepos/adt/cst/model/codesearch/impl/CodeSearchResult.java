/**
 */
package com.devepos.adt.cst.model.codesearch.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.devepos.adt.base.model.adtbase.IResponseMessageList;
import com.devepos.adt.cst.model.codesearch.ICodeSearchObject;
import com.devepos.adt.cst.model.codesearch.ICodeSearchPackage;
import com.devepos.adt.cst.model.codesearch.ICodeSearchResult;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult#getSearchObjects <em>Search
 * Objects</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult#getResponseMessageList
 * <em>Response Message List</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult#getNumberOfResults
 * <em>Number Of Results</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult#getNumberOfSearchedObjects
 * <em>Number Of Searched Objects</em>}</li>
 * <li>{@link com.devepos.adt.cst.model.codesearch.impl.CodeSearchResult#getQueryTimeInMs <em>Query
 * Time In Ms</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CodeSearchResult extends MinimalEObjectImpl.Container implements ICodeSearchResult {
  /**
   * The cached value of the '{@link #getSearchObjects() <em>Search Objects</em>}' containment
   * reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getSearchObjects()
   * @generated
   * @ordered
   */
  protected EList<ICodeSearchObject> searchObjects;

  /**
   * The cached value of the '{@link #getResponseMessageList() <em>Response Message List</em>}'
   * containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getResponseMessageList()
   * @generated
   * @ordered
   */
  protected IResponseMessageList responseMessageList;

  /**
   * The default value of the '{@link #getNumberOfResults() <em>Number Of Results</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumberOfResults()
   * @generated
   * @ordered
   */
  protected static final int NUMBER_OF_RESULTS_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getNumberOfResults() <em>Number Of Results</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumberOfResults()
   * @generated
   * @ordered
   */
  protected int numberOfResults = NUMBER_OF_RESULTS_EDEFAULT;

  /**
   * The default value of the '{@link #getNumberOfSearchedObjects() <em>Number Of Searched
   * Objects</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumberOfSearchedObjects()
   * @generated
   * @ordered
   */
  protected static final int NUMBER_OF_SEARCHED_OBJECTS_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getNumberOfSearchedObjects() <em>Number Of Searched
   * Objects</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getNumberOfSearchedObjects()
   * @generated
   * @ordered
   */
  protected int numberOfSearchedObjects = NUMBER_OF_SEARCHED_OBJECTS_EDEFAULT;

  /**
   * The default value of the '{@link #getQueryTimeInMs() <em>Query Time In Ms</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getQueryTimeInMs()
   * @generated
   * @ordered
   */
  protected static final int QUERY_TIME_IN_MS_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getQueryTimeInMs() <em>Query Time In Ms</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getQueryTimeInMs()
   * @generated
   * @ordered
   */
  protected int queryTimeInMs = QUERY_TIME_IN_MS_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CodeSearchResult() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ICodeSearchPackage.Literals.CODE_SEARCH_RESULT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<ICodeSearchObject> getSearchObjects() {
    if (searchObjects == null) {
      searchObjects = new EObjectContainmentEList<>(ICodeSearchObject.class, this,
          ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS);
    }
    return searchObjects;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public IResponseMessageList getResponseMessageList() {
    return responseMessageList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public NotificationChain basicSetResponseMessageList(
      final IResponseMessageList newResponseMessageList, NotificationChain msgs) {
    IResponseMessageList oldResponseMessageList = responseMessageList;
    responseMessageList = newResponseMessageList;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST, oldResponseMessageList,
          newResponseMessageList);
      if (msgs == null) {
        msgs = notification;
      } else {
        msgs.add(notification);
      }
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setResponseMessageList(final IResponseMessageList newResponseMessageList) {
    if (newResponseMessageList != responseMessageList) {
      NotificationChain msgs = null;
      if (responseMessageList != null) {
        msgs = ((InternalEObject) responseMessageList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST, null, msgs);
      }
      if (newResponseMessageList != null) {
        msgs = ((InternalEObject) newResponseMessageList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
            - ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST, null, msgs);
      }
      msgs = basicSetResponseMessageList(newResponseMessageList, msgs);
      if (msgs != null) {
        msgs.dispatch();
      }
    } else if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST, newResponseMessageList,
          newResponseMessageList));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public int getNumberOfResults() {
    return numberOfResults;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setNumberOfResults(final int newNumberOfResults) {
    int oldNumberOfResults = numberOfResults;
    numberOfResults = newNumberOfResults;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_RESULTS, oldNumberOfResults,
          numberOfResults));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public int getNumberOfSearchedObjects() {
    return numberOfSearchedObjects;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setNumberOfSearchedObjects(final int newNumberOfSearchedObjects) {
    int oldNumberOfSearchedObjects = numberOfSearchedObjects;
    numberOfSearchedObjects = newNumberOfSearchedObjects;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS,
          oldNumberOfSearchedObjects, numberOfSearchedObjects));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public int getQueryTimeInMs() {
    return queryTimeInMs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public void setQueryTimeInMs(final int newQueryTimeInMs) {
    int oldQueryTimeInMs = queryTimeInMs;
    queryTimeInMs = newQueryTimeInMs;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET,
          ICodeSearchPackage.CODE_SEARCH_RESULT__QUERY_TIME_IN_MS, oldQueryTimeInMs,
          queryTimeInMs));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
      final NotificationChain msgs) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      return ((InternalEList<?>) getSearchObjects()).basicRemove(otherEnd, msgs);
    case ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST:
      return basicSetResponseMessageList(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      return getSearchObjects();
    case ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST:
      return getResponseMessageList();
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_RESULTS:
      return getNumberOfResults();
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS:
      return getNumberOfSearchedObjects();
    case ICodeSearchPackage.CODE_SEARCH_RESULT__QUERY_TIME_IN_MS:
      return getQueryTimeInMs();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(final int featureID, final Object newValue) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      getSearchObjects().clear();
      getSearchObjects().addAll((Collection<? extends ICodeSearchObject>) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST:
      setResponseMessageList((IResponseMessageList) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_RESULTS:
      setNumberOfResults((Integer) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS:
      setNumberOfSearchedObjects((Integer) newValue);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__QUERY_TIME_IN_MS:
      setQueryTimeInMs((Integer) newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(final int featureID) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      getSearchObjects().clear();
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST:
      setResponseMessageList((IResponseMessageList) null);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_RESULTS:
      setNumberOfResults(NUMBER_OF_RESULTS_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS:
      setNumberOfSearchedObjects(NUMBER_OF_SEARCHED_OBJECTS_EDEFAULT);
      return;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__QUERY_TIME_IN_MS:
      setQueryTimeInMs(QUERY_TIME_IN_MS_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(final int featureID) {
    switch (featureID) {
    case ICodeSearchPackage.CODE_SEARCH_RESULT__SEARCH_OBJECTS:
      return searchObjects != null && !searchObjects.isEmpty();
    case ICodeSearchPackage.CODE_SEARCH_RESULT__RESPONSE_MESSAGE_LIST:
      return responseMessageList != null;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_RESULTS:
      return numberOfResults != NUMBER_OF_RESULTS_EDEFAULT;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__NUMBER_OF_SEARCHED_OBJECTS:
      return numberOfSearchedObjects != NUMBER_OF_SEARCHED_OBJECTS_EDEFAULT;
    case ICodeSearchPackage.CODE_SEARCH_RESULT__QUERY_TIME_IN_MS:
      return queryTimeInMs != QUERY_TIME_IN_MS_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) {
      return super.toString();
    }

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (numberOfResults: ");
    result.append(numberOfResults);
    result.append(", numberOfSearchedObjects: ");
    result.append(numberOfSearchedObjects);
    result.append(", queryTimeInMs: ");
    result.append(queryTimeInMs);
    result.append(')');
    return result.toString();
  }

} // CodeSearchResult
